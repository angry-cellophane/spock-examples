package org.ka.transformer;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.*;
import org.codehaus.groovy.classgen.BytecodeExpression;

public class AnyCodeVisitor implements GroovyCodeVisitor {
    @Override
    public void visitBlockStatement(BlockStatement statement) {

    }

    @Override
    public void visitForLoop(ForStatement forLoop) {

    }

    @Override
    public void visitWhileLoop(WhileStatement loop) {

    }

    @Override
    public void visitDoWhileLoop(DoWhileStatement loop) {

    }

    @Override
    public void visitIfElse(IfStatement ifElse) {

    }

    @Override
    public void visitExpressionStatement(ExpressionStatement statement) {

    }

    @Override
    public void visitReturnStatement(ReturnStatement statement) {

    }

    @Override
    public void visitAssertStatement(AssertStatement statement) {

    }

    @Override
    public void visitTryCatchFinally(TryCatchStatement finally1) {

    }

    @Override
    public void visitSwitch(SwitchStatement statement) {

    }

    @Override
    public void visitCaseStatement(CaseStatement statement) {

    }

    @Override
    public void visitBreakStatement(BreakStatement statement) {

    }

    @Override
    public void visitContinueStatement(ContinueStatement statement) {

    }

    @Override
    public void visitThrowStatement(ThrowStatement statement) {

    }

    @Override
    public void visitSynchronizedStatement(SynchronizedStatement statement) {

    }

    @Override
    public void visitCatchStatement(CatchStatement statement) {

    }

    @Override
    public void visitMethodCallExpression(MethodCallExpression call) {
        ASTNode receiver = call.getReceiver();
    }

    @Override
    public void visitStaticMethodCallExpression(StaticMethodCallExpression expression) {

    }

    @Override
    public void visitConstructorCallExpression(ConstructorCallExpression expression) {

    }

    @Override
    public void visitTernaryExpression(TernaryExpression expression) {

    }

    @Override
    public void visitShortTernaryExpression(ElvisOperatorExpression expression) {

    }

    @Override
    public void visitBinaryExpression(BinaryExpression expression) {

    }

    @Override
    public void visitPrefixExpression(PrefixExpression expression) {

    }

    @Override
    public void visitPostfixExpression(PostfixExpression expression) {

    }

    @Override
    public void visitBooleanExpression(BooleanExpression expression) {

    }

    @Override
    public void visitClosureExpression(ClosureExpression expression) {

    }

    @Override
    public void visitTupleExpression(TupleExpression expression) {

    }

    @Override
    public void visitMapExpression(MapExpression expression) {

    }

    @Override
    public void visitMapEntryExpression(MapEntryExpression expression) {

    }

    @Override
    public void visitListExpression(ListExpression expression) {

    }

    @Override
    public void visitRangeExpression(RangeExpression expression) {

    }

    @Override
    public void visitPropertyExpression(PropertyExpression expression) {

    }

    @Override
    public void visitAttributeExpression(AttributeExpression attributeExpression) {

    }

    @Override
    public void visitFieldExpression(FieldExpression expression) {

    }

    @Override
    public void visitMethodPointerExpression(MethodPointerExpression expression) {

    }

    @Override
    public void visitConstantExpression(ConstantExpression expression) {

    }

    @Override
    public void visitClassExpression(ClassExpression expression) {

    }

    @Override
    public void visitVariableExpression(VariableExpression expression) {

    }

    @Override
    public void visitDeclarationExpression(DeclarationExpression expression) {

    }

    @Override
    public void visitGStringExpression(GStringExpression expression) {

    }

    @Override
    public void visitArrayExpression(ArrayExpression expression) {

    }

    @Override
    public void visitSpreadExpression(SpreadExpression expression) {

    }

    @Override
    public void visitSpreadMapExpression(SpreadMapExpression expression) {

    }

    @Override
    public void visitNotExpression(NotExpression expression) {

    }

    @Override
    public void visitUnaryMinusExpression(UnaryMinusExpression expression) {

    }

    @Override
    public void visitUnaryPlusExpression(UnaryPlusExpression expression) {

    }

    @Override
    public void visitBitwiseNegationExpression(BitwiseNegationExpression expression) {

    }

    @Override
    public void visitCastExpression(CastExpression expression) {

    }

    @Override
    public void visitArgumentlistExpression(ArgumentListExpression expression) {

    }

    @Override
    public void visitClosureListExpression(ClosureListExpression closureListExpression) {

    }

    @Override
    public void visitBytecodeExpression(BytecodeExpression expression) {

    }
}
