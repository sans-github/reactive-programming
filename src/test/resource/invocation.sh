#!/usr/bin/env bash

functionCallReactiveEndpointWithPrefix() {
  local PREFIX=$1
  local ENDPOINT=$2
  printf "GET http://localhost:58880/$PREFIX/$ENDPOINT\n"
  curl -sk "http://localhost:58880/$PREFIX/$ENDPOINT" -H 'Content-Type: application/json'  | python -m json.tool; curl -s  -o null "http://localhost:58880/$PREFIX/$ENDPOINT" -H 'Content-Type: application/json' -w %{time_total}\\n
  printf "\n"
}

function callReactiveEndpointsOnRxJava() {
    local PREFIX="rxjava"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "sync"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-sync"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-async-with-subscribeOn-rxIoThreadPool"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-async-with-subscribeOn-customThreadPool"
}

function callReactiveEndpointsOnJava9() {
    local PREFIX="java9"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "sync"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-sync"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-async-with-subscribeOn-rxIoThreadPool"
    functionCallReactiveEndpointWithPrefix "$PREFIX" "reactive-async-with-subscribeOn-customThreadPool"
}

callReactiveEndpointsOnRxJava
printf "=======\n\n "
callReactiveEndpointsOnJava9
