/**
 *  @(#) ConditionBiOperatorEvaluator.java
 *
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
 * limitations under the License. 
 **/
package org.jreversepro.ast.evaluator;


import java.util.Arrays;

import org.jreversepro.ast.expression.ConditionExpression;
import org.jreversepro.ast.expression.Expression;
import org.jreversepro.ast.expression.ConditionExpression.RelationalOperator;
import org.jreversepro.reflect.instruction.Instruction;


/**
 * @author akkumar
 * 
 */
public class ConditionBiOperatorEvaluator extends AbstractInstructionEvaluator {

  /**
   * @param context
   */
  public ConditionBiOperatorEvaluator(EvaluatorContext context) {
    super(context);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.sf.jrevpro.decompile.evaluator.AbstractInstructionEvaluator#evaluate
   * (net.sf.jrevpro.reflect.instruction.Instruction)
   */
  @Override
  void evaluate(Instruction ins) {
    Expression rhs = evalMachine.pop();
    Expression lhs = evalMachine.pop();

    RelationalOperator op = RelationalOperator.EQ;
    switch (ins.opcode) {
    case OPCODE_IF_ICMPEQ:
    case OPCODE_IF_ACMPEQ:
      op = RelationalOperator.EQ;
      break;
    case OPCODE_IF_ACMPNE:
    case OPCODE_IF_ICMPNE:
      op = RelationalOperator.NE;
      break;
    case OPCODE_IF_ICMPLT:
      op = RelationalOperator.LT;
      break;
    case OPCODE_IF_ICMPGE:
      op = RelationalOperator.GE;
      break;
    case OPCODE_IF_ICMPGT:
      op = RelationalOperator.GT;
      break;
    case OPCODE_IF_ICMPLE:
      op = RelationalOperator.LE;
      break;
    }
    evalMachine.conditionExpression = new ConditionExpression(lhs, rhs, op);

    addConditional(ins, evalMachine.conditionExpression);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.jrevpro.decompile.evaluator.AbstractInstructionEvaluator#
   * getProcessingOpcodes()
   */
  @Override
  Iterable<Integer> getProcessingOpcodes() {
    return Arrays.asList(OPCODE_IF_ICMPEQ, OPCODE_IF_ACMPEQ, // ==
        OPCODE_IF_ICMPNE, OPCODE_IF_ACMPNE, // !=
        OPCODE_IF_ICMPLT, OPCODE_IF_ICMPLE, // <=
        OPCODE_IF_ICMPGE, OPCODE_IF_ICMPGT); // >=
  }
}
