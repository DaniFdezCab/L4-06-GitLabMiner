package AISS.GitLabMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

        @JsonProperty("id")
        private String id;
        @JsonProperty("body")
        private String body;
        @JsonProperty("created_at")
        private String created_at;
        @JsonProperty("updated_at")
        private String updated_at;

        public Comment(){
        }
        public Comment(String id, String body, String created_at, String updated_at){
            this.id = id;
            this.body = body;
            this.created_at = created_at;
            this.updated_at = updated_at;

        }
        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("body")
        public String getBody() {
            return body;
        }
        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
        }
        @JsonProperty("created_at")
        public String getCreated_at() {
            return created_at;
        }
        @JsonProperty("created_at")
        public void setCreated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        @JsonProperty("updated_at")
        public String getUpdated_at() {
            return updated_at;
        }
        @JsonProperty("updated_at")
        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }


    }
    


