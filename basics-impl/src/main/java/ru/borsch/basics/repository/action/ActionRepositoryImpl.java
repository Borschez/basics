package ru.borsch.basics.repository.action;

import org.springframework.stereotype.Component;
import ru.borsch.basics.model.action.BaseAction;

import java.util.HashMap;
import java.util.Map;

@Component("actionRepository")
public class ActionRepositoryImpl implements ActionRepository {

    private Map<String, BaseAction> actionRepository = new HashMap<>();

    @Override
    public void save(BaseAction action) {
        if (actionRepository.containsKey(action.getId())) {
            throw new IllegalArgumentException(String.format("Action %s already in repository", action.getId()));
        }
        actionRepository.put(action.getId(), action);
    }

    @Override
    public BaseAction getActionById(String id) {
        return actionRepository.get(id);
    }
}
