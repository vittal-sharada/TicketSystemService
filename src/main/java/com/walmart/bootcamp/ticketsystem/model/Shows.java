package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "shows")
public class Shows implements Serializable {

        private static final long serialVersionUID = -7893458373970990714L;
        private static final Logger LOGGER = LoggerFactory.getLogger(Shows.class);

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "show_id", nullable = false, columnDefinition = "integer")
        private Long showId;

        @Size(max = 30)
        @Column(name = "show_name", nullable = false, unique = true, length = 30, columnDefinition = "varchar")
        private String showName;

        @NotNull
        @Size(max = 400)
        @Column(name = "show_description", nullable = false, unique = true, length = 400, columnDefinition = "varchar")
        private String showDescription;

        public Shows() {

        }

        public Shows(String showName, String showDescription) {
                this.showName = showName;
                this.showDescription = showDescription;
        }



}
