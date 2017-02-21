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
 * @author You!
 *
 */
public class FilePattern {
	/**
	 * Creates a new instance of the FilePattern class that filters
	 * file names based on the given pattern.
	 * 
	 * @param pattern the pattern used to filter file names.
	 * @see FilePattern
	 */
	public FilePattern(String pattern) {
		// your implementation
	}

	/**
	 * Returns whether the given filename matches this pattern.
	 * @param filename
	 * @return true if filename matches the pattern
	 */
	public boolean matches(String filename) {
		throw new NotImplementedException();
	}
    
}
