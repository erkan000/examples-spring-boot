# Criteria API

- set your db properties
- run init SQL on the "resources" path

- http://localhost:8080/api/spec
- http://localhost:8080/api/specJoinFetch

POST 
{
    "rating":3
}

# TechnologyLabelSpec.java

- Shows search with a dynamic criteria. Downside is, for inner tables, it makes extra selects for each row!(n+1 problem) Solutions are;
- JOIN FETCH
- GraphParser API
- Tuple and multiselect


# TechnologyLabelSpecJoinFetch.java

- Shows selecting with JOIN FETCH. He makes join with multiple tables with one query. For inner tables there will be no extra select.



- https://thorben-janssen.com/hibernate-tip-left-join-fetch-join-criteriaquery
- https://www.initgrep.com/posts/java/jpa/select-values-in-criteria-queries

