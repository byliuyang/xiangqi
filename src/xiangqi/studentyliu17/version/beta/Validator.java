package xiangqi.studentyliu17.version.beta;

/**
 * Xiangqi validator
 */
@FunctionalInterface
public interface Validator {
    boolean validate(CoordinateImpl c1, CoordinateImpl c2, Object... arguments);
}
