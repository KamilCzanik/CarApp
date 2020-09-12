package xyz.czanik.carapp

interface Container {
    val navigator: Navigator
    val repositoryFactory: RepositoryFactory
}