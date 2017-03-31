/**
 * Movie Object contains title, release year, genre and rating. 
 * User can get the Movie's title, release year, genre and rating. 
 * User can check if two Movies are the same. 
 * User can also display the String representation of the Movie. 
 * @author xellis
 *
 */
public class Movie {
	
	/**
	 * Minimum Movie's release year 
	 */
	private static final int MIN_RELEASE_YEAR = 1880;
	/**
	 * Movie's title 
	 */
	private String title;
	/**
	 * Movie's release year 
	 */
	private int releaseYear;
	/**
	 * Movie's genre 
	 */
	private String genre;
	/**
	 * Movie's rating 
	 */
	private String rating;
	
	/**
	 * Create a Movie object that contains title, release year, genre and rating. 
	 * @param title Movie's title 
	 * @param releaseYear Movie's release year 
	 * @param genre Movie's genre 
	 * @param rating Movie's rating 
	 */
	public Movie(String title, int releaseYear, String genre, String rating) {
		super();
		if (releaseYear < MIN_RELEASE_YEAR) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}
	/**
	 * Return Movie's title. 
	 * @return the title 
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Return Movie's release year. 
	 * @return the releaseYear 
	 */
	public int getReleaseYear() {
		return releaseYear;
	}
	/**
	 * Return Movie's genre. 
	 * @return the genre 
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * Return Movie's rating. 
	 * @return the rating 
	 */
	public String getRating() {
		return rating;
	}
	/** 
	 * Ensure that equivalent objects hash to the same value. 
	 * @return integer 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	/** 
	 * Return true if two Movies have the same values of their fields. 
	 * @param obj Object movie 
	 * @return true if two Movies' fields match 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/**
	 * Return the String representation of the Movie. 
	 * @return String representation of the Movie 
	 */
	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear
				+ ", genre=" + genre + ", rating=" + rating + "]";
	}

}
