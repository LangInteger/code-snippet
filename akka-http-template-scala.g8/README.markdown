# Akka HTTP Template Scala

A [Giter8][g8] template for akka http server!

## How to Use

- sbt new langinteger/akka-http-template-scala.g8
- run in sbt
- get users with `curl localhost:8080/users`
- add user with `curl -H "Content-Type: application/json"  -X POST -d '{"name":"lang","age":9,"countryOfResidence":"CN"}' localhost:8080/users`

## Template license

Written in 2019 by LangInteger

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
