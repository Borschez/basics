package ru.borsch.basics.service.document;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.borsch.basics.model.document.DocumentEntity;
import ru.borsch.basics.model.state.State;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service("documentStateService")
@Transactional
public class DocumentStateServiceImpl implements DocumentStateService {
    private static String STATE_CLASSNAME_TEMPLATE = "ru.borsch.basics.document.state.%s.%s";

    private Class getStateClass(String documentType, String state) {
        if (documentType != null && !documentType.isEmpty() && state != null && !state.isEmpty()) {
            String stateClassName = String.format(STATE_CLASSNAME_TEMPLATE,
                    documentType, state);
            try {
                return Class.forName(stateClassName);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(String.format("No State found with class '%s'", stateClassName), e);
            }
        } else {
            throw new IllegalArgumentException(String.format("Argument is not allowed documentType: '%s' state: '%s'", documentType, state));
        }
    }

    @Override
    public State getState(DocumentEntity documentEntity) {
        if (documentEntity != null && documentEntity.getState() != null && !documentEntity.getState().isEmpty()) {
            try {
                Class stateClass = getStateClass(documentEntity.getDocumentTypeCode(), documentEntity.getState());
                Constructor constructor = stateClass.getConstructor(new Class[]{DocumentEntity.class});
                return (State) constructor.newInstance(documentEntity);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setState(DocumentEntity documentEntity, String state) {
        State previousState = getState(documentEntity);

        if (previousState != null && previousState.getName().equals(state)) return;
        if (previousState != null) {
            previousState.onExit();
        }

        if (state != null && !state.isEmpty()) {
            documentEntity.setState(state);
            State newState = getState(documentEntity);
            newState.onEnter();
        }
    }
}
