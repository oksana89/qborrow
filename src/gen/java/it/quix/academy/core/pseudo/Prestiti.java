package it.quix.academy.core.pseudo;
import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgEditField;
import it.quix.framework.core.codegen.annotation.QgLabel;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.codegen.annotation.SearchFieldType;
import it.quix.framework.core.converter.annotation.QcDateType;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "prestiti")

public class Prestiti implements Serializable {
	   
	@Id
	@ManyToOne
	@JoinColumn(name = "beneficiario")
	@QgSearchField
    @QgSortable
    @QgListColumnField
    public Soggetti soggetti;
	//public String beneficiario;

	@Id
	@ManyToOne
	@JoinColumn(name = "oggetto_prestato")
	@QgEditField(editFieldType = SearchFieldType.COMBO_SEARCH_FIELD)
	@QgSearchField 
	@QgListColumnField
	@Column(nullable = false)
	@QgLabel(label = "oggetto prestato", description = "oggetto prestato")
	public Oggetti oggetti;
	
	 
	@Column(length = 9, nullable = false)
	@QcDateType
	@QgSearchField
	@Temporal(TemporalType.DATE)
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
    @QgLabel(label = "data prestito", description = "data prestito")
    @QgListColumnField
	public Date data_prestito;
	
	
	@Column(name = "data_scadenza_prestito", length = 9, nullable = false)
	@QcDateType
	@QgSearchField
	@QgListColumnField
	@Temporal(TemporalType.DATE)
	@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
    @QgSortable
    @QgLabel(label = "data scadenza prestito", description = "data scadenza prestito")
	public Date data_scadenza_prestito;
	
	
	
	 
}
