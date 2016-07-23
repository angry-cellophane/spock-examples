package org.ka.transformer;


import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.ASTTransformation;
import spock.lang.Specification;

import java.util.List;

public class AnyTransformer implements ASTTransformation{

    final ClassNode specification = ClassHelper.makeWithoutCaching(Specification.class);

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        ModuleNode module = (ModuleNode) nodes[0];
        List<ClassNode> classes = module.getClasses();

        for (ClassNode clazz : classes) {
            if (!isSpec(clazz)) continue;

            clazz.visitContents(new AnyClassVisitor());
        }

    }

    private boolean isSpec(ClassNode clazz) {
        return clazz.isDerivedFrom(specification);
    }
}
