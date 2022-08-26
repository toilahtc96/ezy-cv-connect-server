package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "level")
@SuperBuilder
public class Level extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LevelName name;
    private String meaning;
    private EntityStatus status;
}
