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
package net.sf.jrevpro.output;

import net.sf.jrevpro.reflect.ClassInfo;

public class DisassemblerOutputter implements AbstractOutputter {

  /**
   * Returns the stringified disassembled/decompiled class, optionally with
   * metadata.
   * 
   * @param getBytecode
   *          If TRUE, returns the disassembled code IF the class has already
   *          been disassembled. If FALSE, returns the decompiled code IF the
   *          class has been decompiled. Otherwise, returns null;
   * @param includeMetadata
   *          - TRUE if method stack & exception data should be output.
   * @return Stringified class
   */
  public String output(ClassInfo _clazz) {
    CodeStyler styler = new BSDKNFCodeStyler();
    AbstractClassOutputterImpl impl = new DisassemblerOutputImpl(_clazz, styler);
    impl.process();
    return impl.getContents();

  }

}
