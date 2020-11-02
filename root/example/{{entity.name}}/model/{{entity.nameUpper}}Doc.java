package {{path}}.{{entity.name}}.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class {{entity.nameUpper}}Doc {
    @Id
    {{#entityProperties}}
            {{level}} {{type}} {{name}};
    {{/entityProperties}}
}
