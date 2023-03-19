package interpret

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parse.CobolBaseListener
import parse.CobolBaseVisitor
import parse.CobolParser
import parse.CobolParser.AtomicMoveContext
import parse.CobolParser.IdentifierAtomContext
import parse.CobolParser.LiteralAtomContext
import parse.CobolParser.NonnumericLitContext
import parse.CobolParser.NumericLitContext
import parse.CobolParser.PrimitiveIdContext
import parse.CobolParser.ProcedureSignalContext
import parse.CompileException
import parse.CompileException.Companion.addError
import program.*


class Checker() : CobolBaseVisitor<BabyCobolObject>() {

    override fun visitAcceptStat(ctx: CobolParser.AcceptStatContext?): BabyCobolObject? {
        return super.visitAcceptStat(ctx)
    }

    override fun visitAddStat(ctx: CobolParser.AddStatContext?): BabyCobolObject? {
        return if(Value(ctx!!.to.text).isNumeric()) Value(ctx.add.sumOf{ it.text.toDouble()}.toString())+Value(ctx.to.text)
        else Value(ctx.add.joinToString { it.text })+Value(ctx.to.text)
    }

    override fun visitDisplayStat(ctx: CobolParser.DisplayStatContext?): BabyCobolObject? {
        return super.visitDisplayStat(ctx)
    }

    override fun visitLiteralAtom(ctx: LiteralAtomContext?): BabyCobolObject {
        return Value(ctx!!.literal().text)
    }

    override fun visitMoveStat(ctx: CobolParser.MoveStatContext?): BabyCobolObject? {
        return super.visitMoveStat(ctx)
    }



}