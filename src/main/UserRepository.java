public abstract class UserRepository {
    public abstract User Load(Long id);
    abstract public void saveData(User user);
}
