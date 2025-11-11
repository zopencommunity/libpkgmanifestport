node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/libpkgmanifestport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/libpkgmanifestport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'Library for working with RPM manifests'),
      string(name: 'BUILD_LINE', value: 'DEV')
    ]
  }
}
