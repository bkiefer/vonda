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
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
// 02111-1307, USA.
// =================================================================

package de.dfki.mlt.agent.nlg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.lt.tr.dialogue.cplan.UtterancePlanner;
import de.dfki.lt.tr.dialogue.cplan.functions.FunctionFactory;
import java.util.LinkedHashMap;

public class LanguageGenerator {
  /**
   * The property name for the project file containing the mapper rules
   */
  public static String MAPPER_PROJECT_PROP = "mapper.project";

  /* *************************************************************
   * NL processing constants
   * ************************************************************ */

  /**
   * The property name for the project file containing the language generation
   * and analysis rules. To this property, a dot and the language to generate
   * must be appended to get the usable property.
   */
  public static String NL_GENERATION_PROJECT_PROP = "generation.project";
  public static String NL_ANALYSIS_PROJECT_PROP = "analysis.project";



  private static Logger logger =
      LoggerFactory.getLogger(LanguageGenerator.class);

  private CPlannerNlg cplanner;

  private UtterancePlanner _ruleMapper;

  private InfoStateFunction _infoState;


  public static String langCodeToLangName(String langCode) {
    switch(langCode) {
    case "ita": return "italian";
    case "eng": return "english";
    case "dut": return "dutch";
    default:
      throw new IllegalArgumentException("Unknown lang code: " + langCode);
    }
  }

  private UtterancePlanner getPlanner() {
    return new UtterancePlanner();
  }

  private static Map<String, LanguageGenerator> _generators =
      new HashMap<String, LanguageGenerator>();

  public static LanguageGenerator getGenerator(String currentLang) {
    LanguageGenerator singleton = _generators.get(currentLang);
    if (singleton == null) {
      singleton = new LanguageGenerator(currentLang);
      _generators.put(currentLang, singleton);
    }
    return singleton;
  }

  /**
   * Implement this as a singleton
   *
   * @param lang
   *          a three-character ISO language code for the number conversion
   *          functionality
   */
  private LanguageGenerator(String lang) {
    try {
      setLanguage(lang);
      _ruleMapper = getPlanner();
      _infoState = new InfoStateFunction();
      FunctionFactory.register(_infoState, _ruleMapper);
      // so that all random calls to generation can be recorded, too
      // don't know if this is strictly necessary
      // FunctionFactory.register(new RecordableRandomFunction(), _ruleMapper);
      File foo = new File(configs.get("resource_dir"),
          configs.get("mapper_projject_prop"));
      _ruleMapper.readProjectFile(foo);
    } catch (FileNotFoundException e) {
      logger.error("mapper rules not found: " + e);
    } catch (IOException e) {
      logger.error("mapper rules could not be read: " + e);
    }
  }

  /** Currently, to switch the language, you will create a new (cached)
   *  {@link LanguageGenerator}
   * @param lang
   * @throws IOException
   */
  private void setLanguage(String lang) throws IOException {
    lang = lang.toLowerCase().substring(0, 3);
    cplanner =
        new CPlannerNlg(
            new File(configs.get("resource_dir"),
            configs.get("generation.project" + "." + lang)), lang);
  }

  public void registerAccess(String what, BaseInfoStateAccess access) {
    _infoState.setAccess(what, access);
  }

  public DagNode toDag(String genericDialogueAct) {
    return _ruleMapper.parseLfString(genericDialogueAct);
  }

  /**
   * Put the data from the user model into the generic dialogue act received
   * from the state machine
   *
   * TODO: this will be an improved version of the old
   * LanuageGenerator.getSurfaceFormExtendedLf
   *
   * @param genericDialogueAct
   * @return (optionally) a motion action and a surface string to be uttered by
   *         the TTS
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
  
    private LinkedHashMap<String, String> configs;
  
  /**
   * takes a configuration and sets all properties to those specified in it
   * @param configs
   */
  public void initConfig(LinkedHashMap<String, String> configs){
    this.configs = configs;
    MAPPER_PROJECT_PROP = configs.get("mapper_project_prop");
    NL_GENERATION_PROJECT_PROP = configs.get("generation.project");
    NL_ANALYSIS_PROJECT_PROP = configs.get("analysis.project");
  }
}
