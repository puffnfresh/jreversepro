/**
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2008 Karthik Kumar.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. * 
 */
package org.jreversepro.output;

import java.util.logging.Logger;

import org.jreversepro.CustomLoggerFactory;


/**
 * 
 * @author akkumar
 * 
 */
public class BSDKNFCodeStyler implements CodeStyler {

  public BSDKNFCodeStyler() {
    depth = 0;
  }

  public String closeBlock() {
    if (depth == 0) {
      logger.warning("Depth seems to be zero. Unformatted code. ");
    } else {
      --depth;
    }
    return outputLine("}");
  }

  public String outputLine(String str) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < depth; ++i) {
      sb.append("    ");
    }
    sb.append(str);
    sb.append("\n");
    return sb.toString();
  }

  public String openBlock() {
    ++depth;
    return " {\n";
  }

  private int depth;

  private Logger logger = CustomLoggerFactory.createLogger();

}
