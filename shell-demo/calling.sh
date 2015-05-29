#!/usr/bin/env bash

# Determine the current working directory
_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# Preserve the calling directory
_CALLING_DIR="$(pwd)"

echo "current working directory is ${_DIR}"
echo "calling directory is ${_CALLING_DIR}"


# Determines if a given application is already installed. If not, will attempt
# to install
## Arg1 - application name
## Arg2 - Alternate path to local install under build/ dir
check_and_install_app() {
  # create the local environment variable in uppercase
  local app_bin="`echo $1 | awk '{print toupper(\$0)}'`_BIN"
  echo $app_bin
  # some black magic to set the generated app variable (i.e. MVN_BIN) into the
  # environment
  eval "${app_bin}=`which $1 2>/dev/null`"

  if [ -z "`which $1 2>/dev/null`" ]; then
    echo "开始安装"$1
  else
    echo "已安装$1"
  fi
}


check_and_install_app 'mvn'