package it.quix.academy.core.pseudo;

import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgAttribute;
import it.quix.framework.core.codegen.annotation.QgEditField;
import it.quix.framework.core.codegen.annotation.QgLabel;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.codegen.annotation.SearchFieldType;
import it.quix.framework.core.converter.annotation.QcDateType;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "oggetti")

public class Oggetti implements Serializable{


		@Id
		@Column(length = 50, nullable = false)
		@QgLabel(label = "id", description = "id oggetto")
		@QgSearchField
		@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
	    @QgSortable
	    @QgListColumnField
		public Integer id;


		@Column(length = 50, nullable = false)
		@QgSearchField
		@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
	    @QgSortable
	    @QgListColumnField
	    @QgLabel(label = "proprietario", description = "proprietario")
		public String proprietario;
		
		@Column(length = 225, nullable = true)
		@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0) 
	    @QgSortable
	    @QgListColumnField
	    @QgLabel(label = "titolo", description = "titolo")
		public String titolo;
		
		@Column(length = 225, nullable = true)
		@QgListColumnField
		@QgLabel(label = "descrizione", description = "descrizione")
		public String descrizione;
		
		@Column(nullable = true)  
		@QgListColumnField
		@QgLabel(label = "immagine", description = "immagine")
		public @Lob String immagine;
		
		@Column(length = 225, nullable = true)
		@QgListColumnField
		@QgLabel(label = "categoria", description = "categoria")
		@QgAttribute(type = "QBO000_categoria")
		public String categoria;
		
		
		@Column(length = 9, nullable = false)
		@QcDateType
		@Temporal(TemporalType.DATE)
		@QgOrderBy(type = OrderByTypeEnum.ASC, position = 0)
	    @QgSortable
	    @QgListColumnField
		public Date data_ultima_modifica;
		
		
		@ManyToOne
		@JoinColumn(name = "user_name")
		public @QgEditField(editFieldType = SearchFieldType.COMBO_SEARCH_FIELD) Soggetti soggetti;
	

}
