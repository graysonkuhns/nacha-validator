FROM    node:13

# Copy frontend files
COPY    . /workspace
WORKDIR /workspace

# Install dependencies
RUN     yarn install

# Start the dev server
CMD     ["yarn", "start"]
