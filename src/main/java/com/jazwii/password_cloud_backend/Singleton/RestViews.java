package com.jazwii.password_cloud_backend.Singleton;

public class RestViews {
    public interface PublicMinimal {
    }

    public interface PublicMinimalAll extends PublicMinimal,
            AccountPublicMinimal {
    }

    public interface PublicDetailedAll extends PublicMinimalAll,
            AccountPublicDetailed {
    }

    public interface ExtendedMinimalAll extends PublicMinimalAll {
    }

    public interface ExtendedDetailedAll extends PublicDetailedAll {
    }

    public interface InternalMinimalAll extends ExtendedMinimalAll {
    }

    public interface InternalDetailedAll extends ExtendedDetailedAll {
    }

    public interface AccountPublicMinimal extends PublicMinimal {
    }

    public interface AccountPublicDetailed extends AccountPublicMinimal {
    }

    public interface AccountCriticalDetailed extends AccountPublicDetailed {
    }

    public interface LoginPublicMinimal extends PublicMinimal {
    }

    public interface LoginPublicDetailed extends LoginPublicMinimal, AccountPublicMinimal{
    }

    public interface LoginPublicCriticalMinimal extends PublicMinimal, AccountPublicDetailed{
    }

    public interface LoginCriticalDetailed extends LoginPublicDetailed, LoginPublicCriticalMinimal {
    }

    public interface AddressPublicMinimal extends PublicMinimal {
    }

    public interface AddressPublicDetailed extends AddressPublicMinimal, AccountPublicMinimal{
    }

    public interface AddressPublicCriticalMinimal extends PublicMinimal, AccountPublicDetailed{
    }

    public interface AddressCriticalDetailed extends AddressPublicDetailed, AddressPublicCriticalMinimal {
    }
}