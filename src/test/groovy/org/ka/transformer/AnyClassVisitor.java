package org.ka.transformer;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.spockframework.compiler.AstUtil;

import java.util.List;

public class AnyClassVisitor implements GroovyClassVisitor {

    @Override
    public void visitClass(ClassNode node) {

    }

    @Override
    public void visitConstructor(ConstructorNode node) {

    }

    @Override
    public void visitMethod(MethodNode method) {
        if (!isFeatureMethod(method)) return;

        List<Statement> statements = AstUtil.getStatements(method);
        for (Statement statement : statements) {
            if (statement.getClass() != ExpressionStatement.class) continue;


        }
    }

    private boolean isFeatureMethod(MethodNode method) {
        for (Statement stat : AstUtil.getStatements(method)) {
            String label = stat.getStatementLabel();
            if (label == null || method.isStatic()) continue;

            return true;
        }

        return false;
    }

    @Override
    public void visitField(FieldNode node) {

    }

    @Override
    public void visitProperty(PropertyNode node) {

    }
}
