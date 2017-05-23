// =================================================================
// Copyright (C) 2010-2014 DFKI
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation; either version 2.1 of
// the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
// 02111-1307, USA.
// =================================================================
package de.dfki.mlt.rudimant.agent.nlg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.lt.tr.dialogue.cplan.UtterancePlanner;
import de.dfki.lt.tr.dialogue.cplan.functions.FunctionFactory;
import static de.dfki.mlt.rudimant.agent.nlg.ConfConstants.*;
import java.io.FileReader;
import org.yaml.snakeyaml.Yaml;

public class LanguageGenerator {


  /* *************************************************************
   * NL processing constants
   * ************************************************************ */
  private static Logger logger
          = LoggerFactory.getLogger(LanguageGenerator.class);

  private Map<String, Object> configs;

  private CPlannerNlg cplanner;

  private UtterancePlanner _ruleMapper;

  private InfoStateFunction _infoState;

  public static String langCodeToLangName(String langCode) {
    switch (langCode) {
      case "ita":
        return "italian";
      case "eng":
        return "english";
      case "dut":
        return "dutch";
      default:
        throw new IllegalArgumentException("Unknown lang code: " + langCode);
    }
  }

  private UtterancePlanner getPlanner() {
    return new UtterancePlanner() {
      /** Load all things contained in the configuration in the right way */
      protected void load() {
        // initHierachy();
        /** First load the plugins, then the rules */
        loadPlugins();
        loadRules();
      }
    };
  }

  private static Map<String, LanguageGenerator> _generators
          = new HashMap<String, LanguageGenerator>();

  /** Factory method to get a language generator for the given config.
   *
   * @param configDir   the directory where the config file is (for relative paths)
   * @param currentLang which language should be treated
   * @param langConfig  the configuration to create a new generator
   * @return a new LanguageGenerator for the given language, or null in case
   *         of failure.
   * @throws FileNotFoundException
   */
  public static LanguageGenerator getGenerator(File configDir,
      String currentLang,
      Map<String, Object> langConfig) throws FileNotFoundException {
    LanguageGenerator singleton = _generators.get(currentLang);
    if (singleton == null) {
      singleton = new LanguageGenerator(configDir, currentLang, langConfig);
      _generators.put(currentLang, singleton);
    }
    return singleton;
  }

  /**
   * Implement as a singleton
   *
   * @param lang a three-character ISO language code for the number conversion
   * functionality
   */
  private LanguageGenerator(File configDir, String lang,
      Map<String, Object> cfgs) {
    configs = new HashMap<>(cfgs);
    try {
      setLanguage(configDir, lang);
      _ruleMapper = getPlanner();
      _infoState = new InfoStateFunction();
      FunctionFactory.register(_infoState, _ruleMapper);
      // so that all random calls to generation can be recorded, too
      // don't know if is strictly necessary
      // FunctionFactory.register(new RecordableRandomFunction(), _ruleMapper);
      File mapperProj = new File(configDir, (String) configs.get(MAPPER_PROJECT));
      _ruleMapper.readProjectFile(mapperProj);
    } catch (FileNotFoundException e) {
      logger.error("mapper rules not found: " + e);
    } catch (IOException e) {
      logger.error("mapper rules could not be read: " + e);
    }
  }

  /**
   * Currently, to switch the language, you will create a new (cached)
   * {@link LanguageGenerator}
   *
   * @param lang
   * @throws IOException
   */
  private void setLanguage(File configDir, String lang) throws IOException {
    cplanner = new CPlannerNlg(
        new File(configDir, (String) configs.get(GENERATION_PROJECT)), lang);
  }

  public void registerAccess(String what, BaseInfoStateAccess access) {
    _infoState.setAccess(what, access);
  }

  public DagNode toDag(String genericDialogueAct) {
    return DagNode.parseLfString(genericDialogueAct);
  }

  /**
   * Put the data from the user model into the generic dialogue act received
   * from the state machine
   *
   * TODO: will be an improved version of the old
   * LanuageGenerator.getSurfaceFormExtendedLf
   *
   * @param genericDialogueAct
   * @return (optionally) a motion action and a surface string to be uttered by
   * the TTS
   */
  public Pair<String, String>
          getSurfaceFormExtendedLf(String genericDialogueAct) {
    DagNode rawInput = toDag(genericDialogueAct);
    if (rawInput == null) {
      logger.error("Non-wellformed schema LF: " + genericDialogueAct);
      return new Pair<String, String>("", "ERROR!!!");
    }
    return getSurfaceFormExtendedLf(rawInput);
  }

  public Pair<String, String>
          getSurfaceFormExtendedLf(DagNode rawInput) {
    // obsolete
    // _ruleMapper.schemaLfStringToLfString(genericDialogueAct));
    logger.info("Before mapping: " + rawInput);
    DagNode mappedInput = _ruleMapper.process(rawInput);
    logger.info("After mapping: " + mappedInput);
    ProtoLf toGenerate = new ProtoLf(mappedInput);

    String motion = toGenerate.getEdgeValue("motion");
    if (motion == null) {
      motion = "";
    }

    String text = getSurfaceForm(toGenerate);
    logger.debug("Surface: <{}|{}>", motion, text);
    return new Pair<String, String>(motion, text);
  }

  public String getSurfaceForm(ProtoLf protoLF) {
    String result;
    try {
      result = cplanner.realise(protoLF);
    } catch (Exception e) {
      result = "";
      // text.replace(0, text.length(), "No Result");
      logger.error("protoLogicalForm=" + protoLF);
      e.printStackTrace();
    }
    return result;
  }

  /**
   * takes a configuration and sets all properties to those specified in it
   *
   * @param configs
   */
  public void initConfig(Map<String, Object> cfgs) {
    configs = new HashMap<>(cfgs);
  }

  @SuppressWarnings("unchecked")
  public void readConfig(String confname)
      throws FileNotFoundException {
    Yaml yaml = new Yaml();
    File confFile = new File(confname);
    configs = (Map<String, Object>) yaml.load(new FileReader(confFile));
  }
}
