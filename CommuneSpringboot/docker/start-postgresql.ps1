docker run --detach `
	--name pg-commune `
	--env POSTGRES_DB=dbcommune `
	--env POSTGRES_USER=communadmin `
	--env POSTGRES_PASSWORD=password `
	-p 5432:5432 `
	postgres:18