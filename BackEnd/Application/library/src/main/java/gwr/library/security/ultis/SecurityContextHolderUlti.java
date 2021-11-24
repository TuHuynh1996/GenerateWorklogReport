package gwr.library.security.ultis;

import org.springframework.security.core.context.SecurityContextHolder;

import gwr.library.entity.Users;
import gwr.library.security.UserPrincipal;

/**
 * The Class SecurityContextHolderUlti.
 */
public class SecurityContextHolderUlti {

	/**
	 * Gets the current user.
	 *
	 * @return the current user
	 */
	public static Users getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserPrincipal) {
			return ((UserPrincipal) principal).getUser();
		} else {
			return null;
		}
	}

	/**
	 * Gets the current user name.
	 *
	 * @return the current user name
	 */
	public static String getCurrentUserName() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserPrincipal) {
				return ((UserPrincipal) principal).getUser().getName();
			} else {
				return SecurityContextHolder.getContext().getAuthentication().getName();
			}
		} catch (Exception e) {
			return "Unknow";
		}
	}
}
