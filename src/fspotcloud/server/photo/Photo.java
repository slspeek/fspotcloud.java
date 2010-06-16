/**
 * 
 */
package fspotcloud.server.photo;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author slspeek@gmail.com
 * 
 */
@PersistenceCapable
public class Photo {
	@PrimaryKey
	private String name;

	@Persistent
	private String description;
	
	@Persistent
	private List<String> tagList;

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the tagList
	 */
	public List<String> getTagList() {
		return tagList;
	}
}
