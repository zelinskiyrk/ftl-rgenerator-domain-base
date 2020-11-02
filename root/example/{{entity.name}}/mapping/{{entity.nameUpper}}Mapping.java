package {{path}}.{{entity.name}}.mapping;

import com.zelinskiyrk.blog.base.api.response.SearchResponse;
import com.zelinskiyrk.blog.base.mapping.BaseMapping;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}FullResponse;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}Response;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import lombok.Getter;
import java.util.stream.Collectors;

@Getter
public class {{entity.nameUpper}}Mapping {
    public static class RequestMapping extends BaseMapping<{{entity.nameUpper}}Request, {{entity.nameUpper}}Doc> {

        @Override
        public {{entity.nameUpper}}Doc convert({{entity.nameUpper}}Request {{entity.name}}Request) {
            return {{entity.nameUpper}}Doc.builder()
                {{#entityProperties}}
                    .{{name}}({{entity.name}}Request.get{{nameUpper}}())
                {{/entityProperties}}
                    .build();
        }

        @Override
        public {{entity.nameUpper}}Request unmapping({{entity.nameUpper}}Doc {{entity.name}}Doc) {
            throw new RuntimeException("dont use this");
        }
    }

    public static class ResponseMapping extends BaseMapping<{{entity.nameUpper}}Doc, {{entity.nameUpper}}Response> {

        @Override
        public {{entity.nameUpper}}Response convert({{entity.nameUpper}}Doc {{entity.name}}Doc) {
            return {{entity.nameUpper}}Response.builder()
                {{#entityProperties}}
                    .{{name}}({{entity.name}}Request.get{{nameUpper}}())
                {{/entityProperties}}
                    .build();
        }

        @Override
        public {{entity.nameUpper}}Doc unmapping({{entity.nameUpper}}Response {{entity.name}}Response) {
            throw new RuntimeException("dont use this");
        }
    }

    public static class ResponseFullMapping extends BaseMapping<{{entity.nameUpper}}Doc, {{entity.nameUpper}}FullResponse> {

        @Override
        public {{entity.nameUpper}}FullResponse convert({{entity.nameUpper}}Doc {{entity.name}}Doc) {
            return {{entity.nameUpper}}FullResponse.builder()
                {{#entityProperties}}
                    .{{name}}({{entity.name}}Request.get{{nameUpper}}())
                {{/entityProperties}}
                    .build();
        }

        @Override
        public {{entity.nameUpper}}Doc unmapping({{entity.nameUpper}}FullResponse response) {
            throw new RuntimeException("dont use this");
        }
    }

    public static class SearchMapping extends BaseMapping<SearchResponse<{{entity.nameUpper}}Doc>, SearchResponse<{{entity.nameUpper}}Response>> {
        private ResponseMapping responseMapping = new ResponseMapping();

        @Override
        public SearchResponse<{{entity.nameUpper}}Response> convert(SearchResponse<{{entity.nameUpper}}Doc> searchResponse) {
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );
        }

        @Override
        public SearchResponse<{{entity.nameUpper}}Doc> unmapping(SearchResponse<{{entity.nameUpper}}Response> {{entity.name}}Responses) {
            throw new RuntimeException("dont use this");
        }
    }

    private final RequestMapping request = new RequestMapping();
    private final ResponseMapping response = new ResponseMapping();
    private final ResponseFullMapping responseFull = new ResponseFullMapping();
    private final SearchMapping search = new SearchMapping();

    public static {{entity.nameUpper}}Mapping getInstance(){
        return new {{entity.nameUpper}}Mapping();
    }
}
