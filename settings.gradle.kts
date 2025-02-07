rootProject.name = "yeonuelix"

include("yeonuelix-adapters:adapter-http")
include("yeonuelix-adapters:adapter-persistence")
include("yeonuelix-adapters:adapter-redis")

include("yeonuelix-apps:app-api")
include("yeonuelix-apps:app-batch")

include("yeonuelix-commons")

include("yeonuelix-core:core-domain")
include("yeonuelix-core:core-port")
include("yeonuelix-core:core-service")
include("yeonuelix-core:core-usecase")
