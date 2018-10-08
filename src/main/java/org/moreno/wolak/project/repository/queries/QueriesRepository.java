package org.moreno.wolak.project.repository.queries;

public class QueriesRepository implements IQueriesDao {

	private static QueriesRepository repositorySingletonInstance = null;
	

	public static QueriesRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new QueriesRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public boolean executeQuery() {
		return true;
	}


}
