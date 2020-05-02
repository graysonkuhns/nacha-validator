#!/usr/bin/env bash

curl \
  -X POST \
  -H "Content-Type: application/json" \
  -d @nacha-no-errors.json \
  http://localhost:8080/api/validate
