# Restful Config API
Restful API demo service for Query/Add/Update application's config
* This service makes use of ETag to prevent updates collisions
https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/ETag
1. for adding config request, no extra header needed
2. for updating config request, must provide header: **If-Match**, which contains the hash of config you are modifying upon
3. for getting and updating config, response header also has **If-Match**, which contains the hash of return config
* For prod senario, suggest to use Elasticsearch for json storage, conditional query and better performance
* For prod senario, suggest to use redis to limit API calls for each IP during a period

