package cat.tecnocampus.courseproject.application.exceptions;

import cat.tecnocampus.courseproject.domain.Subscription;

public class SubscriptionDoesNotExistException extends RuntimeException {
    public SubscriptionDoesNotExistException(String username) {
        super(username + " user doesn't have any subscription");
    }

    public SubscriptionDoesNotExistException(String username, String product) {
        super("The subscription of " + username + " user and product " + product + " does not exists");
    }
}
