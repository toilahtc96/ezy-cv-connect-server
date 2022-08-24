package com.ezyfox.cvconnect.entity;

import com.ezyfox.cvconnect.constant.ProcessCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "process")
public class Process extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ProcessCode code;
    private String meaning;
}
