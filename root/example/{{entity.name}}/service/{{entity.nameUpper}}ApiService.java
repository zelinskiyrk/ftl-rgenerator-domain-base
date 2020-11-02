package {{path}}.{{entity.name}}.service;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.RegistrationRequest;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{entity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class {{entity.nameUpper}}ApiService {
    private final {{entity.nameUpper}}Repository {{entity.name}}Repository;
    private final MongoTemplate mongoTemplate;

    public {{entity.nameUpper}}Doc create({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        return {{entity.name}}Doc;
    }

    public Optional<{{entity.nameUpper}}Doc> findById(ObjectId id) {
        return {{entity.name}}Repository.findById(id);
    }

    public SearchResponse<{{entity.nameUpper}}Doc> search(
            SearchRequest request
    ) {
        Criteria criteria = new Criteria();
        if (request.getQuery() != null && request.getQuery() != "") {
            criteria = criteria.orOperator(
                    // TODO: Add Criteria
                    //Criteria.where("firstName").regex(request.getQuery(), "i"),
            );
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, {{entity.nameUpper}}Doc.class);

        query.limit(request.getSize());
        query.skip(request.getSkip());

        List<{{entity.nameUpper}}Doc> {{entity.name}}Docs = mongoTemplate.find(query, {{entity.nameUpper}}Doc.class);
        return SearchResponse.of({{entity.name}}Docs, count);
    }

    public {{entity.nameUpper}}Doc update({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        Optional<{{entity.nameUpper}}Doc> {{entity.name}}DocOptional = {{entity.name}}Repository.findById(request.getId());
        if ({{entity.name}}DocOptional.isPresent() == false) {
            throw new {{entity.nameUpper}}NotExistException();
        }

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Doc.setId(request.getId());
        {{entity.name}}Repository.save({{entity.name}}Doc);

        return {{entity.name}}Doc;
    }

    public void delete(ObjectId id) {
        {{entity.name}}Repository.deleteById(id);
    }
}
