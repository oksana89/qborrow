package it.quix.academy.core.pseudo;

import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgEditField;
import it.quix.framework.core.codegen.annotation.QgLabel;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.converter.annotation.QcDateType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "soggetti")

public class Soggetti implements Serializable {


	@Id
	@Column(name = "user_name", length = 50, nullable = false)
	@QgLabel(label = "user_name", description = "Nome utente")
	@QgSearchField
	@QgListColumnField
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
	public String user_name;
	
	@Column(length = 100, nullable = false)
	@QgLabel(label = "email", description = "email")
	@QgSearchField
	@QgListColumnField
	public String email;
	
	
	@Column(length = 255, nullable = true)
	@QgEditField
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
    @QgListColumnField
	public String ragione_sociale;


	@Column(length = 50, nullable = true)
	@QgSearchField
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
    @QgListColumnField
	public String nome;
	
	
	@Column(length = 50, nullable = true)
	@QgSearchField
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
    @QgListColumnField
    public String cognome;
	
	
	@Column(nullable = true)
	@QgListColumnField
	public @Lob String immagine;
	 
	
	@Column( nullable = false)
	@QcDateType
	@Temporal(TemporalType.DATE)
	@QgListColumnField
	public Date data_ultima_modifica;
	
	@OneToMany(mappedBy="user_name")
	@QgListColumnField
	//@JoinColumn(name = "proprietario")
	public List<Oggetti> oggetti;
	 
	@OneToMany(mappedBy="user_name")
	@QgListColumnField
	//@JoinColumn(name = "beneficiario")
	public List<Prestiti> prestiti;
	
	

}
