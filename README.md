# Demo of Hibernate's @SoftDelete
Check Hibernate 6.4 [ORM User Guide](https://docs.jboss.org/hibernate/orm/6.4/userguide/html_single/Hibernate_User_Guide.html#soft-delete)

This annotation is more sophisticated replacement for deprecated `@Where`. 
You can delete your entity by calling your repository and instead of deletion Hibernate will use the _indicator column_ to mark it as `deleted = true` (or `active = false`). 
All SELECT/UPDATE calls of JPQL or name-derived queries will then automatically ignore all soft-deleted entries. But in case you really need to select such entries (or bring them back to the active status) you unfortunately need to use [native queries](src/main/kotlin/dk/cngroup/softdelete/Product.kt).

You can start the app and check behavior using REST [endpoints](src/main/kotlin/dk/cngroup/softdelete/ProductController.kt).
Here are some test product prepared in H2 database for you:
```json
[
  {
    "id":1,
    "name":"TIE Fighter",
    "active":true
  },
  {
    "id":2,
    "name":"Death Star",
    "active":true
  },
  {
    "id":3,
    "name":"Star Destroyer",
    "active":true
  }
]
```