// Generated from java-escape by ANTLR 4.11.1
package parse;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CobolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CobolVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CobolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CobolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#id_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_div(CobolParser.Id_divContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#program_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_id(CobolParser.Program_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#env_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnv_div(CobolParser.Env_divContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#data_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_div(CobolParser.Data_divContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#file_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_section(CobolParser.File_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#ws_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs_section(CobolParser.Ws_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#ws_sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs_sentence(CobolParser.Ws_sentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#ls_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLs_section(CobolParser.Ls_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#linkage_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkage_section(CobolParser.Linkage_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#proc_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_div(CobolParser.Proc_divContext ctx);
	/**
	 * Visit a parse tree produced by the {@code displayStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplayStat(CobolParser.DisplayStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code acceptStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcceptStat(CobolParser.AcceptStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddStat(CobolParser.AddStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divideStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivideStat(CobolParser.DivideStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code evaluateStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluateStat(CobolParser.EvaluateStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(CobolParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moveStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveStat(CobolParser.MoveStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplyStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyStat(CobolParser.MultiplyStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code performStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPerformStat(CobolParser.PerformStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stopStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopStat(CobolParser.StopStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtractStat}
	 * labeled alternative in {@link CobolParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractStat(CobolParser.SubtractStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#stop_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStop_statement(CobolParser.Stop_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expression(CobolParser.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CobolParser#procedure_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_name(CobolParser.Procedure_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(CobolParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonnumeric}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonnumeric(CobolParser.NonnumericContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link CobolParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(CobolParser.IdentifierContext ctx);
}