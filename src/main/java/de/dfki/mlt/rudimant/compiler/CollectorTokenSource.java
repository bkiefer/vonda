/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

public class CollectorTokenSource implements TokenSource {
  private final TokenSource source;
  private final Set<Integer> collectTokenTypes =
      new HashSet<Integer>();
  private final LinkedList<Token> collectedTokens =
      new LinkedList<Token>();

  public CollectorTokenSource(TokenSource source,
      Collection<Integer> collectTokenTypes) {
    super();
    this.source = source;
    this.collectTokenTypes.addAll(collectTokenTypes);
  }

  /**
   * Returns next token from the wrapped token source. Stores it
   * in a list if necessary.
   */
  @Override
  public Token nextToken() {
    Token nextToken = source.nextToken();
    if (shouldCollect(nextToken)) {
      collectedTokens.add(nextToken);
    }

    return nextToken;
  }

  /**
   * Decide whether collect the token or not.
   */
  protected boolean shouldCollect(Token nextToken) {
    // filter the token by its type
    return collectTokenTypes.contains(nextToken.getType());
  }

  public LinkedList<Token> getCollectedTokens() {
    return collectedTokens;
  }

  @Override
  public String getSourceName() {
    return "Collect hidden channel " + source.getSourceName();
  }

  @Override
  public int getLine() { return source.getLine(); }

  @Override
  public int getCharPositionInLine() { return source.getCharPositionInLine(); }

  @Override
  public CharStream getInputStream() { return source.getInputStream(); }

  @Override
  public void setTokenFactory(TokenFactory<?> factory) {
    source.setTokenFactory(factory);
  }

  @Override
  public TokenFactory<?> getTokenFactory() {
    return source.getTokenFactory();
  }

}
