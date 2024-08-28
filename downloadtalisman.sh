# check talisman exist, if not download it
if [ -f talisman ]; then
  echo 'Talisman already exists'
  exit 0
fi

if [[ $(uname -p) == 'arm' ]]; then
  echo 'arm chip'
  echo 'Downloading the Tailsman application...'
  # download from this https://github.com/thoughtworks/talisman/releases/download/v1.32.0/talisman_darwin_arm64
  wget https://github.com/thoughtworks/talisman/releases/download/v1.32.0/talisman_darwin_arm64
  mv talisman_darwin_arm64 talisman

else 
  echo 'x86 chip'
  echo 'Downloading the Tailsman application...'
  wget https://github.com/thoughtworks/talisman/releases/download/v1.32.0/talisman_darwin_amd64
  mv talisman_darwin_amd64 talisman
fi

chmod +x talisman