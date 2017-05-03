package pl.sternik.mb.linklinks.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


public class Game {

//  @NotNull
//  @Id
	private Long catalogNumber;
//	@NotNull
	private String name;	


	//	@NotNull
	private Long marketValue;	
//	@Size(min=2, max=30, message = "{Size.moneta.opis}")
//	@Size(min=2, max=30, message = "Opis should be in the range [{min}...{max}]")
	private String description;
//	@NotNull
	private Date dateOfAcquisition;
//	@NotEmpty
	private LanguageVersion languageVersion;
//	@NotNull
	private Status status;
//	@NotNull
	private PlatfromVersion platformVersion;
	
	
	
	public static Game produceGame(Long catalogNumber, String name, Long marketValue, String description, Date dateOfAcquisition,
			LanguageVersion languageVersion, PlatfromVersion platformVersion, Status status) {
		Game game = new Game();
		game.catalogNumber = catalogNumber;
		game.name = name;
		game.marketValue = marketValue;
		game.description = description;
		game.dateOfAcquisition = dateOfAcquisition;
		game.languageVersion = languageVersion;
		game.platformVersion = PlatfromVersion.PS3;
		game.status = status;
		return game;
	}
	public Long getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(Long catalogNumber) {
		this.catalogNumber = catalogNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Long marketValue) {
		this.marketValue = marketValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateOfAcquisition() {
		return dateOfAcquisition;
	}
	public void setDateOfAcquisition(Date dateOfAcquisition) {
		this.dateOfAcquisition = dateOfAcquisition;
	}
	public LanguageVersion getLanguageVersion() {
		return languageVersion;
	}
	public void setLanguageVersion(LanguageVersion languageVersion) {
		this.languageVersion = languageVersion;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PlatfromVersion getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(PlatfromVersion platformVersion) {
		this.platformVersion = platformVersion;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((marketValue == null) ? 0 : marketValue.hashCode());
		result = prime * result + ((dateOfAcquisition == null) ? 0 : dateOfAcquisition.hashCode());
		result = prime * result + ((languageVersion == null) ? 0 : languageVersion.hashCode());
		result = prime * result + ((platformVersion == null) ? 0 : platformVersion.hashCode());
		result = prime * result + ((catalogNumber == null) ? 0 : catalogNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (marketValue == null) {
			if (other.marketValue != null)
				return false;
		} else if (!marketValue.equals(other.marketValue))
			return false;
		if (dateOfAcquisition == null) {
			if (other.dateOfAcquisition != null)
				return false;
		} else if (!dateOfAcquisition.equals(other.dateOfAcquisition))
			return false;
		if (languageVersion == null) {
			if (other.languageVersion != null)
				return false;
		} else if (!languageVersion.equals(other.languageVersion))
			return false;
		if (catalogNumber == null) {
			if (other.catalogNumber != null)
				return false;
		} else if (!catalogNumber.equals(other.catalogNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (platformVersion == null) {
			if (other.platformVersion != null)
				return false;
		} else if (!platformVersion.equals(other.platformVersion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [catalogNumber=" + catalogNumber +", Name=" + name + ", Description=" + description + ", market value="
				+ marketValue + ", language version=" + languageVersion + ", data Nabycia=" + dateOfAcquisition + ", platforma="
				+ platformVersion + ", status=" + status + "]";
	}

	
}
