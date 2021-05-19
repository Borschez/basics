package ru.borsch.basics.service.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.borsch.basics.model.action.BaseAction;
import ru.borsch.basics.repository.action.ActionRepository;

@Service("actionService")
public class ActionServiceImpl implements ActionService{

    private ActionRepository actionRepository;

    @Autowired
    public void setActionRepository(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public BaseAction getActionById(String actionId) {
        return actionRepository.getActionById(actionId);
    }
}
