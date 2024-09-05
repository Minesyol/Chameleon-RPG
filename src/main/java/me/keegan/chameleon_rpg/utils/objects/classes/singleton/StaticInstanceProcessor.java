package me.keegan.chameleon_rpg.utils.objects.classes.singleton;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("me.keegan.chameleon_rpg.utils.objects.classes.singleton.StaticInstance")
public final class StaticInstanceProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                Set<String> modifierNames = element.getModifiers().stream()
                        .map(javax.lang.model.element.Modifier::toString)
                        .collect(Collectors.toSet());

                if (!modifierNames.contains(Modifier.STATIC.toString())) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Field " + element.getSimpleName() + " is not static");
                }
            }
        }

        return true;
    }
}