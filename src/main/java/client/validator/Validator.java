package client.validator;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

public interface Validator<T> {

    boolean validate(T object);
}
