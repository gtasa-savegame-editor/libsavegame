#!/bin/bash

if [[ "$TRAVIS_REPO_SLUG" == "gtasa-savegame-editor/libsavegame" ]] && [[ "$TRAVIS_JDK_VERSION" == "openjdk11" ]] && [[ "$TRAVIS_PULL_REQUEST" == "false" ]] && [[ "$TRAVIS_BRANCH" == "master" ]]; then

  echo -e "Publishing javadoc...\n"

  cp -R target/site/apidocs "${HOME}"/javadoc-latest

  cd "${HOME}" || exit 1
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://"${GH_TOKEN}"@github.com/gtasa-savegame-editor/libsavegame gh-pages > /dev/null

  cd gh-pages || exit 1
  git rm -rf ./javadoc
  cp -Rf "${HOME}"/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Latest javadoc for travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi
