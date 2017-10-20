package it.quix.academy.qborrrow.core.validation;



import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import it.quix.academy.qborrrow.core.model.Soggetti;
import it.quix.framework.core.model.UserContext;
import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.framework.core.validation.api.InvalidConstraint;









import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Validator for the Soggetti object.
 * Annotation validation is automatically performed.
 * To add custom validation insert code into customValidation method and fill an InvalidConstraint set.
 *
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettiValidator extends QborrrowAbstractValidator<Soggetti> {
	Calendar now = Calendar.getInstance();
	Soggetti soggetti = new Soggetti();
	 
     Date dataCompl = soggetti.getDataCompleanno();
     Calendar dob = Calendar.getInstance();
     dob.setTiime;
     Date dateNow = new Date();

     int year1 = now.get(Calendar.YEAR);
     int year2 = dob.get(Calendar.YEAR);
     int age = year1 - year2;
     int month1 = now.get(Calendar.MONTH);
     int month2 = dob.get(Calendar.MONTH);
     if (month2 > month1) {
         age--;
     } else if (month1 == month2) {
         int day1 = now.get(Calendar.DAY_OF_MONTH);
         int day2 = dob.get(Calendar.DAY_OF_MONTH);
         if (day2 > day1) {
             age--;
         }
         
     }
     if ((dataCompl.after(dateNow))) {
     	InvalidConstraint<Soggetti> ic =
         new InvalidConstraintImpl<Soggetti>(Soggetti.class, "error.nonNato", propertyPath + "dataCompleanno",
         soggetti, soggetti.getDataCompleanno());
     	errors.add(ic);
		}
     if (age<18) {
     	InvalidConstraint<Soggetti> ic2 =
         new InvalidConstraintImpl<Soggetti>(Soggetti.class, "error.minorenne", propertyPath + "dataCompleanno",
         soggetti, soggetti.getDataCompleanno());
     	errors.add(ic2);
		}

    private static Log log = LogFactory.getLog(SoggettiValidator.class);

    /**
     * Perform custom validation on a Soggetti object
     * 
     * @param userContext user context of the logged user
     * @param errors set of invalid constraint to fill with this method, if errors are present
     * @param propertyPath the path of the object in the objectGraph. It uses
     *            .notation like user.address[0]e
     * @param soggetti object to validate
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     */
    @Override
    protected void customValidation(UserContext userContext, Set<InvalidConstraint<?>> errors, String propertyPath, Soggetti soggetti, String... groups) {
        if (log.isDebugEnabled()) {
            log.debug("Call customValidation method for class SoggettiValidator ");
        }

        // insert here custom validations for Soggetti model
        // after a validation check fail create a new InvalidContraint of the validated type
        // and instantiate an InvalidContraintImpl of the validated type with the error information, es:
        // InvalidConstraint<Type> ic = new InvalidConstraintImpl<Type>(Type.class, message, propertyPath, rootBean, value);
        // where:
        // [message] is the name of a bundle which contains the appropriate error message
        // [propertyPath] is the path from the root bean to the property which generated the error
        // [rootBean] is the root bean being validated
        // [value] is the value of the property which generated the error
        // in the end, add the new invalid constraint to the errors array, es:
        // errors.add(ic);
    }

	@Override
	protected void customValidation(UserContext userContext,
			Set<InvalidConstraint<?>> errors, String propertyPath,
			Soggetti object, String... groups) {
		// TODO Auto-generated method stub
		
	}

}