package ru.borsch.basics.model.action;

public abstract class WrappedAction implements BaseAction {
    protected abstract void beforeExecute(ActionContext context);

    protected abstract void execute(ActionContext context);

    protected abstract void afterExecute(ActionContext context);

    public void executeAction(ActionContext context) {
        beforeExecute(context);
        execute(context);
        afterExecute(context);
    }
}
