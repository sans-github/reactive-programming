#!/usr/bin/env bash

alias callReactiveEndpoints=functionCallReactiveEndpoints
functionCallReactiveEndpoints() {
  functionCallReactiveEndpoint "sync"
  functionCallReactiveEndpoint "reactive-sync"
  functionCallReactiveEndpoint "reactive-async-with-subscribeOn-rxIoThreadPool"
  functionCallReactiveEndpoint "reactive-async-with-subscribeOn-customThreadPool"
}

functionCallReactiveEndpoint() {
  local endpoint=$1
  printf "==Calling $endpoint endpoint at http://localhost:58880/$endpoint==\n"
  curl -sk  http://localhost:58880/$endpoint -H 'Content-Type: application/json'  | python -m json.tool; curl -s  -o null http://localhost:58880/$endpoint -H 'Content-Type: application/json' -w %{time_total}\\n
  printf "\n\n"
}

callReactiveEndpoints
