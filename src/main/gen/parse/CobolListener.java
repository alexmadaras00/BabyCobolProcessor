// Generated from java-escape by ANTLR 4.11.1
package parse;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CobolParser}.
 */
public interface CobolListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CobolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CobolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CobolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#id_div}.
	 * @param ctx the parse tree
	 */
	void enterId_div(CobolParser.Id_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#id_div}.
	 * @param ctx the parse tree
	 */
	void exitId_div(CobolParser.Id_divContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#program_id}.
	 * @param ctx the parse tree
	 */
	void enterProgram_id(CobolParser.Program_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#program_id}.
	 * @param ctx the parse tree
	 */
	void exitProgram_id(CobolParser.Program_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#env_div}.
	 * @param ctx the parse tree
	 */
	void enterEnv_div(CobolParser.Env_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#env_div}.
	 * @param ctx the parse tree
	 */
	void exitEnv_div(CobolParser.Env_divContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#data_div}.
	 * @param ctx the parse tree
	 */
	void enterData_div(CobolParser.Data_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#data_div}.
	 * @param ctx the parse tree
	 */
	void exitData_div(CobolParser.Data_divContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#file_section}.
	 * @param ctx the parse tree
	 */
	void enterFile_section(CobolParser.File_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#file_section}.
	 * @param ctx the parse tree
	 */
	void exitFile_section(CobolParser.File_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#ws_section}.
	 * @param ctx the parse tree
	 */
	void enterWs_section(CobolParser.Ws_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#ws_section}.
	 * @param ctx the parse tree
	 */
	void exitWs_section(CobolParser.Ws_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#ws_sentence}.
	 * @param ctx the parse tree
	 */
	void enterWs_sentence(CobolParser.Ws_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#ws_sentence}.
	 * @param ctx the parse tree
	 */
	void exitWs_sentence(CobolParser.Ws_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#ls_section}.
	 * @param ctx the parse tree
	 */
	void enterLs_section(CobolParser.Ls_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#ls_section}.
	 * @param ctx the parse tree
	 */
	void exitLs_section(CobolParser.Ls_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#linkage_section}.
	 * @param ctx the parse tree
	 */
	void enterLinkage_section(CobolParser.Linkage_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#linkage_section}.
	 * @param ctx the parse tree
	 */
	void exitLinkage_section(CobolParser.Linkage_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#proc_div}.
	 * @param ctx the parse tree
	 */
	void enterProc_div(CobolParser.Proc_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#proc_div}.
	 * @param ctx the parse tree
	 */
	void exitProc_div(CobolParser.Proc_divContext ctx);
	/**
	 * Enter a parse tree produced by the {@code displayStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDisplayStat(CobolParser.DisplayStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code displayStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDisplayStat(CobolParser.DisplayStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code acceptStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAcceptStat(CobolParser.AcceptStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code acceptStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAcceptStat(CobolParser.AcceptStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAddStat(CobolParser.AddStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAddStat(CobolParser.AddStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divideStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDivideStat(CobolParser.DivideStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divideStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDivideStat(CobolParser.DivideStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code evaluateStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEvaluateStat(CobolParser.EvaluateStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code evaluateStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEvaluateStat(CobolParser.EvaluateStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(CobolParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(CobolParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moveStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterMoveStat(CobolParser.MoveStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moveStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitMoveStat(CobolParser.MoveStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplyStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyStat(CobolParser.MultiplyStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplyStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyStat(CobolParser.MultiplyStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code performStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPerformStat(CobolParser.PerformStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code performStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPerformStat(CobolParser.PerformStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stopStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStopStat(CobolParser.StopStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stopStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStopStat(CobolParser.StopStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subtractStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSubtractStat(CobolParser.SubtractStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subtractStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSubtractStat(CobolParser.SubtractStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#stop_statement}.
	 * @param ctx the parse tree
	 */
	void enterStop_statement(CobolParser.Stop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#stop_statement}.
	 * @param ctx the parse tree
	 */
	void exitStop_statement(CobolParser.Stop_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(CobolParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(CobolParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CobolParser#procedure_name}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_name(CobolParser.Procedure_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CobolParser#procedure_name}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_name(CobolParser.Procedure_nameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(CobolParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(CobolParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nonnumeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterNonnumeric(CobolParser.NonnumericContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nonnumeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitNonnumeric(CobolParser.NonnumericContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(CobolParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(CobolParser.IdentifierContext ctx);
}