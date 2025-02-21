# Use a lightweight Node.js image
FROM node:23 AS base

ENV NODE_ENV=development

# Set working directory
WORKDIR /src

# Build
FROM base AS build

COPY --link package.json ./
RUN npm install

# Run
FROM base

COPY --from=build /src/node_modules /src/node_modules

CMD [ "npm", "run", "dev" ]