package by.bsuir.discussion.event;

public record InTopicMessage(
    Operation operation,
    MessageInTopicTo messageDto
){
    public enum Operation {
        GET_ALL,
        GET_BY_ID,
        CREATE,
        UPDATE,
        DELETE_BY_ID
    }

    public InTopicMessage(Operation operation) {
        this(operation, null);
    }
}
