/**
 * Filters file names using command-line wildcards.
 *
 * '*' matches any number of character.
 * '?' matches exactly one character.
 *
 * Examples:
 * '*.md' matches all files with the markdown extension.
 * 'exercise_??.md' matches, for example, 'exercise_01.md'.
 * 
 * @author Samuel Schwegler, Alain Stulz
 *
 */

import java.lang.*;

public class FilePattern {

	private String pattern;

	/**
	 * Creates a new instance of the FilePattern class that filters
	 * file names based on the given pattern.
	 * 
	 * @param pattern the pattern used to filter file names.
	 * @see FilePattern
	 */
	public FilePattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * Returns whether the given filename matches this pattern.
	 * @param filename
	 * @return true if filename matches the pattern
	 */
	public boolean matches(String filename) {
		// Base case
		if (filename.length() == 0 || pattern.length() == 0) {
			return (filename.length() == 0 && pattern.length() == 0) || pattern.equals("*");
		}

		// Recursively match filename
		if (pattern.charAt(0) == '?') {
			// Compare next characters
			return new FilePattern(pattern.substring(1)).matches(filename.substring(1));
		} else if (pattern.charAt(0)== '*') {
			if (pattern.length() == 1) {
				return true;
			}

			// Check if any characters match after the asterisk
			boolean hasMatchAfterAsterisk = false;
			int i = 0;
			while (!hasMatchAfterAsterisk && i < filename.length()) {
				hasMatchAfterAsterisk = pattern.charAt(1)== filename.charAt(i);
				i++;
			}

			return hasMatchAfterAsterisk && new FilePattern(pattern.substring(1)).matches(filename.substring(i-1));
		} else {
			return (pattern.charAt(0) == filename.charAt(0)) && new FilePattern(pattern.substring(1)).matches(filename.substring(1));
		}
	}
}
